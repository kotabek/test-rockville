package com.kotabek.utils;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by kotabek on 6/1/17.
 */
public class IdGenerator {
    private Random random = new Random(System.currentTimeMillis());

    public String nextId() {
        return new BigInteger(130, random).toString(32);
    }
}
