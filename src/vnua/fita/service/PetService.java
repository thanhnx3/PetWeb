package vnua.fita.service;


import java.util.List;

import vnua.fita.bean.Pet;

public interface PetService {

	public List<Pet> search(String keyword);
	
	public boolean insertPet(Pet pet);

	public boolean updatePet(Pet pet);

	public boolean deletePet(int petId);

	public Pet selectPet(int petId);
}
