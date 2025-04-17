package tn.esprit.gaspillagezero.controllers.Event_Management_Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.EventManagement.Menus;
import tn.esprit.gaspillagezero.services.EventManagement_Servvice.IMenuService;

import java.util.List;

@RestController
@RequestMapping("/Menu")
@CrossOrigin(origins = "http://localhost:59887") // adjust frontend domain as needed
// Adjust if needed
public class MenusController {

    private final String uploadDir = "C:/uploads/";

    @Autowired
    IMenuService menuService;
/*
    // ✅ POST with image upload
    @PostMapping("/addmenu")
    public ResponseEntity<?> addMenuWithImage(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("price") double price,
            @RequestPart(value = "file", required = false) MultipartFile file) {
        try {
            String imagePath = null;

            if (file != null && !file.isEmpty()) {
                String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

                Path pathToUse = Paths.get(uploadDir);
                if (file.getContentType() != null && file.getContentType().startsWith("image")) {
                    pathToUse = pathToUse.resolve("images");
                } else if (file.getContentType() != null && file.getContentType().startsWith("video")) {
                    pathToUse = pathToUse.resolve("videos");
                } else {
                    return ResponseEntity.badRequest().body("Unsupported file type");
                }

                if (!Files.exists(pathToUse)) {
                    Files.createDirectories(pathToUse);
                }

                Path fullPath = pathToUse.resolve(originalFilename);
                file.transferTo(fullPath.toFile());

                String typeFolder = file.getContentType().startsWith("image") ? "images" : "videos";
                imagePath = "http://localhost:8089/uploads/" + typeFolder + "/" + originalFilename;
            }

            Menus menu = new Menus();
            menu.setName(name);
            menu.setDescription(description);
            menu.setImagePath(imagePath);
            menu.setStartDate(startDate);
            menu.setEndDate(endDate);
            menu.setPrice(price);

            Menus savedMenu = menuService.addMenu(menu);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMenu);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }*/

    @PostMapping ("/addmenu")
    public Menus addMenu(@RequestBody Menus menu) {
        return menuService.updateMenu(menu);
    }

    @PutMapping("/updatemenu")
    public Menus updateMenu(@RequestBody Menus menu) {
        return menuService.updateMenu(menu);
    }

    @GetMapping("/retreiveAllMenus")
    public List<Menus> retieveAllmenus() {
        return menuService.retieveAllmenus();
    }

    @GetMapping("/retriveMenu/{menuId}")
    public Menus retieveMenu(@PathVariable int menuId) {
        return menuService.retieveMenu(menuId);
    }

    @DeleteMapping("/deletemenu/{menuId}")
    public void deleteMenu(@PathVariable long menuId) {
        menuService.deleteMenu(menuId);
    }
}
