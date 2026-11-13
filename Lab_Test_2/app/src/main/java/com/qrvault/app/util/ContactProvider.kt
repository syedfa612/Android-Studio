package com.qrvault.app.util

import android.content.Context
import android.provider.ContactsContract
import com.qrvault.app.model.Contact

/** Reads real contacts from the device through the ContactsContract content provider. */
object ContactProvider {

    /** Requires READ_CONTACTS. Throws SecurityException if the permission is missing. */
    fun loadContacts(context: Context, unnamedLabel: String): List<Contact> {
        val resolver = context.contentResolver
        val phones = HashMap<Long, MutableList<String>>()
        val emails = HashMap<Long, MutableList<String>>()

        resolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                ContactsContract.CommonDataKinds.Phone.NUMBER
            ),
            null, null, null
        )?.use { c ->
            val idIdx = c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val numIdx = c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER)
            while (c.moveToNext()) {
                val number = c.getString(numIdx)?.trim().orEmpty()
                if (number.isEmpty()) continue
                val list = phones.getOrPut(c.getLong(idIdx)) { mutableListOf() }
                val key = number.filter { it.isDigit() || it == '+' }
                if (list.none { existing -> existing.filter { it.isDigit() || it == '+' } == key }) {
                    list.add(number)
                }
            }
        }

        resolver.query(
            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
            arrayOf(
                ContactsContract.CommonDataKinds.Email.CONTACT_ID,
                ContactsContract.CommonDataKinds.Email.ADDRESS
            ),
            null, null, null
        )?.use { c ->
            val idIdx = c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.CONTACT_ID)
            val addrIdx = c.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Email.ADDRESS)
            while (c.moveToNext()) {
                val address = c.getString(addrIdx)?.trim().orEmpty()
                if (address.isEmpty()) continue
                val list = emails.getOrPut(c.getLong(idIdx)) { mutableListOf() }
                if (list.none { it.equals(address, ignoreCase = true) }) list.add(address)
            }
        }

        val result = ArrayList<Contact>()
        resolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            arrayOf(
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY
            ),
            null, null,
            "${ContactsContract.Contacts.DISPLAY_NAME_PRIMARY} COLLATE NOCASE ASC"
        )?.use { c ->
            val idIdx = c.getColumnIndexOrThrow(ContactsContract.Contacts._ID)
            val nameIdx = c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)
            while (c.moveToNext()) {
                val id = c.getLong(idIdx)
                val contactPhones = phones[id].orEmpty()
                val contactEmails = emails[id].orEmpty()
                val name = c.getString(nameIdx)?.trim().orEmpty()
                    .ifEmpty { contactPhones.firstOrNull() ?: contactEmails.firstOrNull() ?: unnamedLabel }
                result.add(Contact(id, name, contactPhones, contactEmails))
            }
        }
        return result
    }
}
