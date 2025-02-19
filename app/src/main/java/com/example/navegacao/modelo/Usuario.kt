package com.example.navegacao.modelo

import com.google.firebase.firestore.DocumentId

class Usuario {

    @DocumentId
    var id: String = ""
    var nome: String = ""
    var senha: String = ""

    constructor()

    constructor(id: String, nome: String, senha: String)
}