package com.example.domain.repository

interface Repository {
  suspend fun loadImages()
}