package com.soues.projet2.model

import kotlin.random.Random

val URLS = arrayOf(
    "https://pixabay.com/static/uploads/photo/2014/04/02/17/02/girl-307747_960_720.png",
    "https://clamart-lafontaine-blog.e-monsite.com/medias/images/eleve.png",
    "https://ekladata.com/2NvmX2GdczA71ZMewxFwyR9CesE@350x586.png"
)

data class EleveBean(var nom: String, var prenom: String) {
    val url = URLS[Random.nextInt(URLS.size)]
}

data class Resultat(var record: List<Record>)

class Record(var fields: Fields)

class Fields(var commune: String, var adresse: String, var detail: String, var etab: String, var cpcomm: Int)
