package com.ddfinv.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddfinv.backend.dto.AssignClientDTO;
import com.ddfinv.backend.dto.ClientDTO;
import com.ddfinv.backend.service.ClientService;
import com.ddfinv.backend.service.PermissionHandlerService;
import com.ddfinv.core.entity.Client;

@RestController
@RequestMapping("/api/employees")
public class ClientController {
    
    private final ClientService clientService;
    private final PermissionHandlerService permissionHandlerService;

    @Autowired
    public ClientController(ClientService clientService, PermissionHandlerService permissionHandlerService){
        this.clientService= clientService;
        this.permissionHandlerService = permissionHandlerService;
    }

    @PostMapping
    public ResponseEntity<?> createNewEmployeEntity(@RequestBody ClientDTO clientDTO){
        if (!permissionHandlerService.currentUserHasPermission("CREATE_CLIENT")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        ClientDTO newClient = clientService.createClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
    }

    @GetMapping
    public ResponseEntity<?> getAllClients(){
        if(!permissionHandlerService.currentUserHasPermission("VIEW_CLIENTS")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<ClientDTO> clientList = clientService.getAllClients();
        return ResponseEntity.ok(clientList);
    }

    @PostMapping("/assign")
    public ResponseEntity<?> assignClientToEmployee(@RequestBody AssignClientDTO assignClientDTO){
        if (!permissionHandlerService.currentUserHasPermission("ASSIGN_CLIENT")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        ClientDTO assignClient = clientService.assignClientToEmployee(assignClientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(assignClient);
    }



    @GetMapping("/by-employee/{employeeId}")
    public ResponseEntity<?> getClientsByEmployeeId(@PathVariable String employieeId){
        if(!permissionHandlerService.currentUserHasPermission("VIEW_CLIENTS")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<ClientDTO> clientList= clientService.getClientsByEmployeeId(employieeId);
        return ResponseEntity.ok(clientList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id){
        if (!permissionHandlerService.currentUserHasPermission("VIEW_CLIENT")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ClientDTO retrievedClient = clientService.getClientById(id);
        return ResponseEntity.ok(retrievedClient);
    }

    @GetMapping("/client-id/{clientId}")
    public ResponseEntity<?> getClientByClientId(@PathVariable String clientId){
        if (!permissionHandlerService.currentUserHasPermission("VIEW_CLIENT")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ClientDTO retrievedClient = clientService.getClientByClientId(clientId);
        return ResponseEntity.ok(retrievedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClientEntity(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        if (!permissionHandlerService.currentUserHasPermission("EDIT_CLIENT")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        ClientDTO updatedEmployee = clientService.updateClient(id, clientDTO);
        return ResponseEntity.ok(updatedEmployee);
    }


}
