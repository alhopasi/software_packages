package com.software_packages.controller;

import com.software_packages.domain.PackageManager;
import com.utils.PackageReader;
import com.utils.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    
    private PackageManager manager;


    public MainController() {
        Reader reader = new PackageReader();
        manager = new PackageManager(reader);
    }
    
    @GetMapping(value = {"/"})
    public String index(Model model) {
        manager.clearPackages();
        manager.getPackages();
        manager.sortPackagesByName();
        model.addAttribute("packageManager", manager);
        return "index";
    }

    @GetMapping(value = {"package/{packageId}"})
    public String index(Model model, @PathVariable String packageId) {
        if (!packageId.matches("-?(0|[1-9]\\d*)")) {
            return "index";
        }

        if (manager.getPackage(0) == null) {
            manager.getPackages();
            manager.sortPackagesByName();
        }
        model.addAttribute("packageManager", manager);
        model.addAttribute("package",manager.getPackage(Integer.valueOf(packageId)));
        return "pack";
    }
}
