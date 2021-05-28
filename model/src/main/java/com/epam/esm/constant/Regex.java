package com.epam.esm.constant;

public final class Regex {
    public static final String USER_NAME = "[\\w]{1,45}";
    public static final String USER_SURNAME = "[\\w]{1,45}";
    public static final String PASSWORD = "[\\w\\W]{6,256}";
    public static final String TAG_NAME = "[\\w]{1,45}";
    public static final String CERTIFICATE_NAME = "[ \\w]{1,100}";
    public static final String CERTIFICATE_DESCRIPTION = "[\\w\\s-,.:!?]{1,300}";
    public static final int MIN_ID = 1;

    public static final int MIN_CERTIFICATE_DURATION = 1;
    public static final int CERTIFICATE_PRICE_INTEGER = 5;
    public static final int CERTIFICATE_PRICE_FRACTION = 2;
    public static final String CERTIFICATE_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSS";
    public static final String TIMEZONE = "UTC";
    public static final int MAX_CERTIFICATE_DURATION = 100;

}
