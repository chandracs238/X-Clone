package com.pcs.x.todo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pcs.x.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Todo {

	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	private String name;
	private LocalDate startDate;
	private LocalDate targetDate;
	private boolean isCompleted;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Todo() {
	}

	public Todo(@NotNull String name, LocalDate startDate, LocalDate targetDate, boolean isCompleted, User user) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.targetDate = targetDate;
		this.isCompleted = isCompleted;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", startDate=" + startDate + ", targetDate=" + targetDate
				+ ", isCompleted=" + isCompleted + "]";
	}
	

}
