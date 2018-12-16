package com.luxoft.logeek.p6spy;

public class ToHexStringConverter {

  private static final char[] HEX_CHARS = {
          '0', '1', '2', '3',
          '4', '5', '6', '7',
          '8', '9', 'A', 'B',
          'C', 'D', 'E', 'F'
  };

  public String toHexString(byte[] bytes) {
    StringBuilder sb = new StringBuilder();
    for (byte b : bytes) {
      int temp = (int) b & 0xFF;
      sb.append(HEX_CHARS[temp / 16]);
      sb.append(HEX_CHARS[temp % 16]);
    }
    return sb.toString();
  }

  public String patched_toHexString(byte[] bytes) {
    StringBuilder sb = new StringBuilder(bytes.length * 2);
    for (byte b : bytes) {
      int temp = (int) b & 0xFF;
      sb.append(HEX_CHARS[temp / 16]);
      sb.append(HEX_CHARS[temp % 16]);
    }
    return sb.toString();
  }

  public String chars_toHexString(byte[] bytes) {
    char[] chars = new char[bytes.length * 2];
    for (int i = 0; i < bytes.length; i++) {
      byte b = bytes[i];
      int j = i * 2;
      int temp = (int) b & 0xFF;
      chars[j] = HEX_CHARS[temp / 16];
      chars[j + 1] = HEX_CHARS[temp % 16];
    }
    return String.valueOf(chars);
  }
}
