package com.staroski.cursomc.services.validation;

import com.staroski.cursomc.domain.Cliente;
import com.staroski.cursomc.dto.ClienteDTO;
import com.staroski.cursomc.repositories.ClienteRepository;
import com.staroski.cursomc.resources.exception.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository repository;

    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
        Map<String, String> map = (Map<String, String>) request
                .getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Optional<Cliente> cliente = repository.findByEmail(objDto.getEmail());

        if (cliente.isPresent() && !cliente.get().getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        // inclua os testes aqui, inserindo erros na lista
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}