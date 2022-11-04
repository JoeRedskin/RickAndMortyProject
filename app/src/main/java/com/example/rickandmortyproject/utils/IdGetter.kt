package com.example.rickandmortyproject.utils

fun getIdList(urlList: List<String>?): List<Int> {
    val idList = mutableListOf<Int>()
    if (!urlList.isNullOrEmpty()) {
        for (url in urlList) {
            idList.add(getId(url))
        }
    }
    return idList.toList()
}

fun getId(url: String): Int {
    val regex = """(\d+)""".toRegex()
    return regex.find(url)?.value?.toInt() ?: 0
}