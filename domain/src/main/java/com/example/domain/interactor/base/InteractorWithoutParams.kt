package com.example.domain.interactor.base

interface InteractorWithoutParams<out T> {
  suspend operator fun invoke(): T
}