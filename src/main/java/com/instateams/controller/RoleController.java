package com.instateams.controller;

import com.instateams.controller.flashmessage.FlashMessage;
import com.instateams.model.Role;
import com.instateams.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RoleController
{
    @Autowired
    private RoleService roleService;

    @RequestMapping("/roles")
    public String allRoles(Model model)
    {
        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);
        if (!model.containsAttribute("role"))
        {
            model.addAttribute("role", new Role());
            model.addAttribute("action", "/roles");
        }

        return "role/index";
    }

    @RequestMapping(value = "/roles", method = RequestMethod.POST)
    public String addRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.role", result);
            redirectAttributes.addFlashAttribute("role", role);
            return "redirect:/roles";
        }

        roleService.save(role);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role successfully added", FlashMessage.Status
                .SUCCESS));

        return "redirect:/roles";
    }

    @RequestMapping("/roles/{id}")
    public String displayEditForm(@PathVariable Long id, Model model)
    {
        Role role = roleService.findById(id);
        model.addAttribute("role", role);
        model.addAttribute("action", String.format("/roles/%s", id));
        return "role/index";
    }

    @RequestMapping(value = "/roles/{id}", method = RequestMethod.POST)
    public String editRole(@Valid Role role, BindingResult result, RedirectAttributes redirectAttributes)
    {
        if (result.hasErrors())
        {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.role", result);
            redirectAttributes.addFlashAttribute("role", role);
            return String.format("redirect:/roles/%s", role.getId());
        }

        roleService.save(role);

        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role successfully updated", FlashMessage.Status
                .SUCCESS));

        return "redirect:/roles";
    }

    @RequestMapping(value = "/roles/{id}/delete")
    public String deleteRole(@PathVariable Long id, RedirectAttributes redirectAttributes)
    {
        Role role = roleService.findById(id);
        roleService.delete(role);
        redirectAttributes.addFlashAttribute("flash", new FlashMessage("Role successfully deleted.", FlashMessage.Status
                .SUCCESS));
        return "redirect:/roles";
    }
}
