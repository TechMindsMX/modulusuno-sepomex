package com.tim.modulusuno.sepomex.domain

import org.springframework.data.repository.PagingAndSortingRepository

interface SepomexRepository extends PagingAndSortingRepository<Sepomex,String> {
  List<Sepomex> findAllByDCodigo(String cp)
}
