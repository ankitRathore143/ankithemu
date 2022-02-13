package com.example.marvalentertainment.security

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.text.Charsets.UTF_8

public class Encryption {

    fun String.md5(): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(toByteArray())).toString(16).padStart(32, '0')
    }

}