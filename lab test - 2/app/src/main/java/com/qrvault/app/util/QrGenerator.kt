package com.qrvault.app.util

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel

object QrGenerator {

    /** Builds a vCard 3.0 string. Missing phone/email entries are simply omitted. */
    fun buildVCard(name: String, phones: List<String>, emails: List<String>): String {
        val fullName = name.trim()
        val split = fullName.lastIndexOf(' ')
        val given = if (split > 0) fullName.substring(0, split) else fullName
        val family = if (split > 0) fullName.substring(split + 1) else ""

        val sb = StringBuilder()
        sb.append("BEGIN:VCARD\n")
        sb.append("VERSION:3.0\n")
        sb.append("N:").append(escape(family)).append(';').append(escape(given)).append(";;;\n")
        sb.append("FN:").append(escape(fullName)).append('\n')
        phones.map { it.trim().replace("\n", " ") }.filter { it.isNotEmpty() }.forEach {
            sb.append("TEL;TYPE=CELL:").append(it).append('\n')
        }
        emails.map { it.trim().replace("\n", "") }.filter { it.isNotEmpty() }.forEach {
            sb.append("EMAIL;TYPE=INTERNET:").append(it).append('\n')
        }
        sb.append("END:VCARD")
        return sb.toString()
    }

    private fun escape(value: String): String = value
        .replace("\\", "\\\\")
        .replace(";", "\\;")
        .replace(",", "\\,")
        .replace("\n", "\\n")

    /**
     * Encodes [content] as a black-on-white QR bitmap (best contrast for scanners).
     * Throws on failure; callers must catch.
     */
    fun generate(content: String, size: Int = 900): Bitmap {
        require(content.isNotBlank()) { "QR content is empty" }
        val hints = HashMap<EncodeHintType, Any>()
        hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
        hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.M
        hints[EncodeHintType.MARGIN] = 2

        val matrix = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints)
        val w = matrix.width
        val h = matrix.height
        val pixels = IntArray(w * h)
        for (y in 0 until h) {
            val offset = y * w
            for (x in 0 until w) {
                pixels[offset + x] = if (matrix.get(x, y)) Color.BLACK else Color.WHITE
            }
        }
        return Bitmap.createBitmap(pixels, w, h, Bitmap.Config.ARGB_8888)
    }
}
