package com.factorysalad.javastudy.E_Collection_Map;

import java.util.Objects;

public class Person {
	private String name;
	private String address;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(name, person.name) && Objects.equals(address, person.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, address);
	}
}