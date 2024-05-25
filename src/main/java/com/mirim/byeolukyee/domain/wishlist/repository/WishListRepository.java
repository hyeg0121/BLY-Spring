package com.mirim.byeolukyee.domain.wishlist.repository;

import com.mirim.byeolukyee.domain.wishlist.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
}
