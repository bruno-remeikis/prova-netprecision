package com.netprecision.prova.repositories;

import com.netprecision.prova.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>
{

}
