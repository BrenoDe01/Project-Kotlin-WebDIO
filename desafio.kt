package com.example.projectkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

class Usuario(val nome: String, val email: String) {

}

data class ConteudoEducacional(val nome: String, val duracao: Int = 60)

data class Formacao(val nome: String, val nivel: Nivel, val conteudos: MutableList<ConteudoEducacional> = mutableListOf()) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }

    override fun toString(): String {
        return "Formacao: $nome Nivel: $nivel, Inscritos: $inscritos, Conteudos: $conteudos"
    }
}

fun listarFormacoesPorNivel(nivel: Nivel, formacoes: List<Formacao>): List<Formacao> {
    return formacoes.filter { it.nivel == nivel }
}

fun main() {
    val conteudo1 = ConteudoEducacional("Introdução ao Kotlin", 60)
    val conteudo2 = ConteudoEducacional("Estruturas de Controle em Kotlin", 90)

    val usuario1 = Usuario("Alice", "alice@email.com")
    val usuario2 = Usuario("Bob", "bob@email.com")

    val formacao1 = Formacao("Curso de Kotlin Básico", Nivel.BASICO, mutableListOf(conteudo1, conteudo2))
    val formacao2 = Formacao("Curso de Kotlin Avançado", Nivel.INTERMEDIARIO, mutableListOf(conteudo1, conteudo2))

    formacao1.matricular(usuario1)
    formacao2.matricular(usuario1)
    formacao2.matricular(usuario2)

    println(formacao1)
    println(formacao2)

    val formacoesBasicas = listarFormacoesPorNivel(Nivel.BASICO, listOf(formacao1, formacao2))
    println("Formações Básicas: $formacoesBasicas")
}

