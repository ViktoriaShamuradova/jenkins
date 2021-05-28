package com.epam.esm.constant;

public final class Message {
    public static final String NOT_BE_BLANK = "must not be blank";
    public static final String USER_NAME = "name should contain from 1 to 45 characters";
    public static final String USER_SURNAME = "surname should contain from 1 to 45 characters";
    public static final String DATE_CANNOT_DEFINE = "create date cannot be defined";
    public static final String PASSWORD = "password can contain latin and " +
            "cyrillic characters, punctuation marks and special characters '@#$%^&*()'. Length must be between " +
            "6 and 256 characters";
    public static final String MUST_BE_FILLED = "must be filled";
    public static final String USER_USERNAME = "username should be valid. Example, xxxxxxx@xxxxx.domain";
    public static final String TAG_NAME = "tag name must contain from 1 to 45 characters without punctuation marks";
    public static final String ENTER_PRICE = "Enter price";
    public static final String ENTER_COUNT = "Enter count";
    public static final String CERTIFICATE_NAME = "name must contain from 1 to 100 characters without punctuation marks";
    public static final String CERTIFICATE_DESCRIPTION = "description must contain from 1 to 300 characters with punctuation marks";
    public static final String CERTIFICATE_DESCRIPTION_OR_NAME = "name or "+CERTIFICATE_DESCRIPTION;
    public static final String ORDER_ITEM_MAX_COUNT= "Exceeding the maximum quantity " + OrderConst.MAX_COUNT_ITEM;
}
