package com.stretching.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.stretching.entity.Youtube;
import com.stretching.entity.emf.UniqueEntityManagerFactory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

@Service
public class YoutubeService {

	public void save(Youtube youtube) {
		EntityManagerFactory emf = UniqueEntityManagerFactory.emf;
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			System.out.println(youtube.toString());
			em.persist(youtube);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
		}
	}

	public List<String> select_all() {
		EntityManagerFactory emf = UniqueEntityManagerFactory.emf;
		EntityManager em = emf.createEntityManager();
		List<String> youtubeList = null;
		try {
			String jpql = "select y from Youtube y order by y.seq desc";
			youtubeList = em.createQuery(jpql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return youtubeList;
	}
}
