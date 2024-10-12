package com.ecommerce.petCare.service;

import com.ecommerce.petCare.model.Pet;
import com.ecommerce.petCare.model.PetShop;
import com.ecommerce.petCare.model.Species;
import com.ecommerce.petCare.repository.PetRepo;
import com.ecommerce.petCare.request.CreatePetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImp implements PetService{

    @Autowired
    private SpeciesService speciesService;

    @Autowired
    private PetRepo petRepo;

    @Override
    public Pet createPet(CreatePetRequest request, PetShop petShop) throws Exception {

        Species species = speciesService.findSpeciesById(request.getSpeciesId());

        Pet pet = new Pet();
        pet.setSpecies(species);
        pet.setPetShop(petShop);
        pet.setBreed(request.getBreed());
        pet.setDescription(request.getDescription());
        pet.setImages(request.getImages());
        pet.setName(request.getName());
        pet.setPrice(request.getPrice());
        pet.setAge(request.getAge());
        pet.setGender(request.getGender());
        pet.setCreationDate(LocalDateTime.now());
        pet.setAvailable(request.isAvailable());

        Pet savedPet = petRepo.save(pet);
        petShop.getPets().add(savedPet);
        return savedPet;
    }

    @Override
    public void deletePet(Long petId) throws Exception {

        Pet pet = findPetById(petId);
        petRepo.delete(pet);

//        Pet pet1 = findPetById(petId);
//        pet1.setPetShop(null);
//        petRepo.save(pet1);

    }


    @Override
    public List<Pet> searchPets(String keyword) throws Exception {
        return petRepo.findPetBySearchQuery(keyword);
    }


    @Override
    public List<Pet> getPetShopPet(Long petShopId, String species, String breed, String gender) {

        List<Pet> pets = petRepo.findByPetShopId(petShopId);

        if(species!=null && !species.equals("")){
            pets=filterBySpecies(pets,species);
        }

        if(breed!=null && !breed.equals("")){
            pets=filterByBreed(pets,breed);
        }

        if(gender!=null && !gender.equals("")){
            pets=filterByGender(pets,gender);
        }


        return pets;
    }

    private List<Pet> filterBySpecies(List<Pet> pets, String species) {

        return pets.stream().filter(petResult->{
            if (petResult.getSpecies()!=null){
                return petResult.getSpecies().getName().equals(species);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Pet> filterByBreed(List<Pet> pets, String breed) {

        return pets.stream().filter(petResult->{
            if (petResult.getBreed()!=null){
                return petResult.getBreed().equals(breed);
            }
            return false;
        }).collect(Collectors.toList());

    }

    private List<Pet> filterByGender(List<Pet> pets, String gender) {

        return pets.stream().filter(petResult->{
            if (petResult.getGender()!=null){
                return petResult.getGender().equals(gender);
            }
            return false;
        }).collect(Collectors.toList());
    }


    @Override
    public Pet findPetById(Long petId) throws Exception {

        Optional<Pet> pet = petRepo.findById(petId);
        if(pet.isEmpty()){
            throw new Exception("Pet not exist...");
        }
        return pet.get()    ;
    }

    @Override
    public Pet updateAvailableStatus(Long petId) throws Exception {

        Pet pet = findPetById(petId);
        pet.setAvailable(!pet.isAvailable());
        return petRepo.save(pet);
    }

    @Override
    public List<Pet> getAllPets() throws Exception {
        return petRepo.findAll();
    }
}
