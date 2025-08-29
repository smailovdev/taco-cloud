package tacos.web;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import tacos.Ingredient;
import tacos.Taco;
import tacos.data.TacoRepository;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/tacos", produces = "application/json")
@CrossOrigin(origins="http://tacocloud:8080")
public class TacoController {
    private TacoRepository tacoRepo;
    private RestTemplate rest;

    public TacoController(TacoRepository tacoRepo, RestTemplate rest) {
        this.tacoRepo = tacoRepo;
        this.rest = rest;
    }

    @GetMapping(params="recent")
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(
                0,
                12,
                Sort.by("createdAt").descending());
        return  tacoRepo.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }

    public Ingredient getIngredientById(String ingredient) {
        return rest.getForObject("http://localhost:8080/ingredients/{id}",
                Ingredient.class,
                ingredient);
    }

    public void updateIngredient(Ingredient ingredient) {
        rest.put("http://localhost:8080/ingredients/{id}",
                ingredient, ingredient.getId());
    }

    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredients/{id}",
                ingredient.getId());
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredients",
                ingredient, Ingredient.class);
    }
}
