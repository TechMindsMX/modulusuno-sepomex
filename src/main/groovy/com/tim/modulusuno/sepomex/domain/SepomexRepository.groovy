package com.team.one.domain

import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository extends PagingAndSortingRepository<Sepomex,Integer> {
  def findById(Integer id)
}
