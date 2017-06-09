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

/**
 *
 *
 * @author Ativelox {@literal <ativelox.dev@web.de>}
 *
 */
public class ImageCache implements Serializable {

	private static final String FILEPATH_SERIALIZATION_PRE = "cache/image_";
	private static final String FILEPATH_SERIALIZATION_SUFF = ".ser";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ImageCache deserialize(final ECachedObject mCachedObject) {
		ImageCache cache = null;
		try (final ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream(buildSerializationPath(mCachedObject)))) {

			cache = (ImageCache) ois.readObject();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();

		}
		return cache;

	}

	public static boolean hasSerializedCache(final ECachedObject mCachedObject) {
		final File cacheFile = new File(buildSerializationPath(mCachedObject));
		return cacheFile.exists() && !cacheFile.isDirectory();
	}

	private static String buildSerializationPath(final ECachedObject mCachedObject) {
		return FILEPATH_SERIALIZATION_PRE + mCachedObject + FILEPATH_SERIALIZATION_SUFF;

	}

	private final HashMap<Long, byte[]> cache;

	private final ECachedObject cachedObject;

	/**
	 * 
	 */
	public ImageCache(final ECachedObject mCachedObject) {
		File cacheDir = new File("cache/");
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		
		this.cache = new HashMap<>();
		this.cachedObject = mCachedObject;

	}

	public void clear() {
		this.cache.clear();

	}

	public HashMap<Long, Image> getCache() {
		HashMap<Long, Image> returnedCache = new HashMap<>();

		for (final Entry<Long, byte[]> entry : this.cache.entrySet()) {
			returnedCache.put(entry.getKey(), byteArrayToImage(entry.getValue()));

		}

		return returnedCache;
	}

	// public Image getImage(final String mImageName) {
	// return this.cache.get(mImageName);
	//
	// }

	public boolean hasImage(final String mImageName) {
		return this.cache.containsKey(mImageName);

	}

	public void putImage(final long mID, final String mImageName) {
		EImageType imageType = EImageType.valueOf(this.cachedObject.toString());

		if (imageType == null) {
			return;

		}

		try {
			BufferedImage image = DDragon.getImage(imageType, EImageStyle.LOADING, mImageName);
			this.cache.put(Long.valueOf(mID), imageToByteArray(image));

		} catch (NumberFormatException | InvalidURLException | IOException | UnsupportedImageStyleException e) {
			// TODO: Remove Debug Print!
			// TODO: Remove Debug Print!
			System.out.println(e);
			System.out.println("An Error occured while trying to add the following image to the cache: " + mImageName);
		}
	}

	public void serialize() {

		try (final ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream(buildSerializationPath(this.cachedObject)))) {
			oos.writeObject(this);

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	private static byte[] imageToByteArray(final BufferedImage mImage) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(mImage, "png", baos);
			return baos.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();

		}
		return new byte[] {};

	}

	private static Image byteArrayToImage(final byte[] mByteArray) {
		return new ImageIcon(mByteArray).getImage();

	}
}
