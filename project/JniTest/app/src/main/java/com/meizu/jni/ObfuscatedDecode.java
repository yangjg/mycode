package com.meizu.jni;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * A utility class used to replace string literals in Java source code with an
 * obfuscated representation of the string. Client applications should use this
 * class to implement the {@link de.schlichtherle.license.LicenseParam},
 * {@link de.schlichtherle.license.KeyStoreParam} and
 * {@link de.schlichtherle.license.CipherParam} interfaces in order to make it
 * considerably hard (although still not impossible) for a reverse engineer to
 * find these string literals while providing comparably fast operation and
 * minimum memory footprint.
 * <p>
 * To use this class you need to provide the string literal to obfuscate as a
 * parameter to the static {@link #obfuscate} method. Its return value is a
 * string which contains the Java code which you should substitute for the
 * string literal in the client application's source code.
 * <p>
 * Please note that obfuscation is <em>not</em> equal to encryption: In contrast
 * to the obfuscation provided by this class, encryption is comparably slow and
 * expensive in terms of resources - no matter what algorithm is actually used.
 * More importantly, encrypting string literals in Java code does not really
 * increase the privacy of these strings compared to obfuscation as long as the
 * encryption key is still placed in the Java code itself and tracing the calls
 * to the JVM is possible. Hence, obfuscation is selected in favour of
 * encryption.
 * <p>
 * In order to provide a reasonable level of security for your application, you
 * should <em>always</em> obfuscate the <em>application code</em> too, including
 * this class. Otherwise, a reverse engineer could simply use the UNIX "strings"
 * utility to search for all usages of this class, which would render its use
 * completely pointless! In case you're looking for a free Java code obfuscation
 * tool for this task, please consider ProGuard, available and usable for free
 * at <a
 * href="http://proguard.sourceforge.net">http://proguard.sourceforge.net</a>.
 * <p>
 * This class is designed to be thread safe.
 * 
 * @author Christian Schlichtherle
 */
public final class ObfuscatedDecode {

	//
	// Regular string constants:
	// These can't be obfuscated using this class because it would cause an
	// illegal recursion in the class initializer.
	// Still we want these strings to be obfuscated in a minimal way in order
	// to prevent a reverse engineer from locating the obfuscated version
	// of this class (created with e.g. ProGuard) by searching for these
	// strings.
	// This is not a 100% secure protection, but it's supposed to make
	// the life of a reverse engineer a little bit harder.
	//

	private static final String UTF8 = new String( new char[] { '\u0055', '\u0054', '\u0046',
			'\u0038' } ); // => "UTF8"

	/**
	 * Encodes a long value to eight bytes in little endian order, beginning at
	 * index <code>off</code>. This is the inverse of
	 * {@link #toLong(byte[], int)}. If less than eight bytes are remaining in
	 * the array, only these low order bytes of the long value are processed and
	 * the complementary high order bytes are ignored.
	 * 
	 * @param l
	 *            The long value to encode.
	 * @param bytes
	 *            The array which holds the encoded bytes upon return.
	 * @param off
	 *            The offset of the bytes in the array.
	 */
	private static final void toBytes( long l, byte[] bytes, int off ) {
		final int end = Math.min( bytes.length, off + 8 );
		for( int i = off; i < end; i++ ) {
			bytes[ i ] = ( byte )l;
			l >>= 8;
		}
	}

	/** The clear text string. */
	private final String s;

	/**
	 * Decodes an obfuscated string from its representation as an array of
	 * longs.
	 * 
	 * @param obfuscated
	 *            The obfuscated representation of the string.
	 * 
	 * @throws NullPointerException
	 *             If <code>obfuscated</code> is <code>null</code>.
	 * @throws ArrayIndexOutOfBoundsException
	 *             If the provided array does not contain at least one element.
	 */
	public ObfuscatedDecode(final long[] obfuscated) {
		final int length = obfuscated.length;

		// The original UTF8 encoded string was probably not a multiple
		// of eight bytes long and is thus actually shorter than this array.
		final byte[] encoded = new byte[ 8 * ( length - 1 ) ];

		// Obtain the seed and initialize a new PRNG with it.
		final long seed = obfuscated[ 0 ];
		final Random prng = new Random( seed );

		// De-obfuscate.
		for( int i = 1; i < length; i++ ) {
			final long key = prng.nextLong();
			toBytes( obfuscated[ i ] ^ key, encoded, 8 * ( i - 1 ) );
		}

		// Decode the UTF-8 encoded byte array into a string.
		// This will create null characters at the end of the decoded string
		// in case the original UTF8 encoded string was not a multiple of
		// eight bytes long.
		final String decoded;
		try {
			decoded = new String( encoded, UTF8 );
		} catch( UnsupportedEncodingException ex ) {
			throw new AssertionError( ex ); // UTF-8 is always supported
		}

		// Cut off trailing null characters in case the original UTF8 encoded
		// string was not a multiple of eight bytes long.
		final int i = decoded.indexOf( 0 );
		s = i != -1 ? decoded.substring( 0, i ) : decoded;
	}

	/**
	 * Returns the original string which was obfuscated by the
	 * {@link #obfuscate} method.
	 */
	public String toString() {
		return s;
	}
}
