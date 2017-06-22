package de.ativelox.leaguestats.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import de.ativelox.leaguestats.constants.ECachedObject;
import de.ativelox.leaguestats.constants.EImageStyle;
import de.ativelox.leaguestats.constants.EImageType;
import de.ativelox.leaguestats.exceptions.InvalidURLException;
import de.ativelox.leaguestats.exceptions.UnsupportedImageStyleException;
import de.ativelox.leaguestats.logging.ELogLevel;
import de.ativelox.leaguestats.logging.ILogger;
import de.ativelox.leaguestats.logging.LoggerFactory;

/**
 * Is able to cache {@link ECachedObject}s by using Serialization. Stores its
 * data in {@link ImageCache#cache}.
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public final class ImageCache implements Serializable {

	/**
	 * The filepath prefix for the cached Data.
	 */
	public static final String FILEPATH_SERIALIZATION_PRE = "cache/image_";

	/**
	 * The filepath suffix for the cached Data.
	 */
	public static final String FILEPATH_SERIALIZATION_SUFF = ".ser";

	/**
	 * The serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The logger used for logging.
	 */
	private final static ILogger logger = LoggerFactory.getLogger();

	/**
	 * Deserializes the current cache for the given {@link ECachedObject}. For
	 * safety {@link ImageCache#hasSerializedCache(ECachedObject)} should be
	 * called first, to secure such a cache exists.
	 * 
	 * @param mCachedObject
	 *            The {@link ECachedObject} of which to get the cache.
	 * 
	 * @return The deserialized {@link ImageCache}.
	 */
	public static ImageCache deserialize(final ECachedObject mCachedObject) {
		ImageCache cache = null;
		try (final ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(buildSerializationPath(mCachedObject)))) {

			cache = (ImageCache) ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			logger.log("An error occured while trying to deserialize the cache for: " + mCachedObject.toString(),
					ELogLevel.ERROR);

		}
		return cache;

	}

	/**
	 * Checks whether a cache exists for the given {@link ECachedObject}.
	 *
	 * @param mCachedObject
	 *            The {@link ECachedObject} of which to check whether a cache
	 *            exists.
	 * 
	 * @return <tt>true</tt> if such a cache exists, <tt>false</tt> if not.
	 */
	public static boolean hasSerializedCache(final ECachedObject mCachedObject) {
		final File cacheFile = new File(buildSerializationPath(mCachedObject));
		return cacheFile.exists() && !cacheFile.isDirectory();
	}

	/**
	 * Builds the path to the serialized file for a given {@link ECachedObject}.
	 * 
	 * @param mCachedObject
	 *            The {@link ECachedObject} of which to get the file path.
	 * 
	 * @return the filepath mentioned.
	 */
	private static String buildSerializationPath(final ECachedObject mCachedObject) {
		return FILEPATH_SERIALIZATION_PRE + mCachedObject + FILEPATH_SERIALIZATION_SUFF;

	}

	/**
	 * Converts a given <b>byte[]</b> to a <b>Image</b>.
	 * 
	 * @param mByteArray
	 *            The byte[] which to convert to an Image.
	 * 
	 * @return The image read from the byte[].
	 */
	private static Image byteArrayToImage(final byte[] mByteArray) {
		return new ImageIcon(mByteArray).getImage();

	}

	/**
	 * Converts a given <b>Image</b> to a <b>byte[]</b>
	 * 
	 * @param mImage
	 *            The image which to convert to a byte[].
	 * @return The byte[] mentioned or an empty byte[] if the image couldn't be
	 *         written to a byte[].
	 */
	private static byte[] imageToByteArray(final BufferedImage mImage) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(mImage, "png", baos);
			return baos.toByteArray();

		} catch (IOException e) {
			logger.log("An error occured while trying to convert an image to a byte array...", ELogLevel.ERROR);

		}
		return new byte[] {};

	}

	/**
	 * The Map used for serialization of images (byte[]).
	 */
	private final HashMap<Long, byte[]> cache;

	/**
	 * This caches cachedObject.
	 */
	private final ECachedObject cachedObject;

	/**
	 * Creates a new ImageCache for the given {@link ECachedObject}.
	 * {@link ImageCache#serialize()} should be called after adding everything
	 * to this ImageCache to save it.
	 */
	public ImageCache(final ECachedObject mCachedObject) {
		File cacheDir = new File("cache/");
		if (!cacheDir.exists()) {
			cacheDir.mkdirs();
		}

		this.cache = new HashMap<>();
		this.cachedObject = mCachedObject;

	}

	/**
	 * Clears this {@link ImageCache#cache}.
	 */
	public void clear() {
		this.cache.clear();

	}

	/**
	 * Gets {@link ImageCache#cache} which is internally used to managed this
	 * cache. Also converts the <b>byte[]</b> to <b>Image</b> by calling
	 * {@link ImageCache#byteArrayToImage(byte[])}.
	 * 
	 * @return the hashmap containing <b>Images</b>.
	 */
	public HashMap<Long, Image> getCache() {
		HashMap<Long, Image> returnedCache = new HashMap<>();

		for (final Entry<Long, byte[]> entry : this.cache.entrySet()) {
			returnedCache.put(entry.getKey(), byteArrayToImage(entry.getValue()));

		}

		return returnedCache;
	}

	/**
	 * Checks if the given ID is contained in this cache.
	 * 
	 * @param mImageID
	 *            The ID mentioned.
	 * 
	 * @return <tt>true</tt> if the ID exists in this cache, <tt>false</tt>
	 *         otherwise.
	 */
	public boolean hasImage(final long mImageID) {
		return this.cache.containsKey(Long.valueOf(mImageID));

	}

	/**
	 * Puts an Image in this cache with the given ID. Converts the fetched
	 * <b>Image</b> to a <b>byte[]</b> by calling
	 * {@link ImageCache#imageToByteArray(BufferedImage)}.
	 * 
	 * @param mID
	 *            The id which to store in this cache.
	 * @param mImageName
	 *            The name with which to fetch the Image.
	 */
	public void putImage(final long mID, final String mImageName) {
		EImageType imageType = EImageType.valueOf(this.cachedObject.toString());

		if (imageType == null) {
			return;

		}

		try {
			BufferedImage image = DDragon.getImage(imageType, EImageStyle.LOADING, mImageName);
			this.cache.put(Long.valueOf(mID), imageToByteArray(image));

		} catch (NumberFormatException | InvalidURLException | IOException | UnsupportedImageStyleException e) {
			logger.log("An error occured while trying to get the image for: " + mImageName, ELogLevel.ERROR);

		}
	}

	/**
	 * Serializes this ImageCache by writing it as file.
	 */
	public void serialize() {
		try (final ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(buildSerializationPath(this.cachedObject)))) {
			oos.writeObject(this);

		} catch (IOException e) {
			logger.log("An error occured while trying to serialize the cache for: " + this.cachedObject.toString(),
					ELogLevel.ERROR);

		}
	}
}
