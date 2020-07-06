package br.com.santander.gastosapi.controllers;

import br.com.santander.gastosapi.domain.Categoria;
import br.com.santander.gastosapi.dtos.SugestaoCategoriaDTO;
import br.com.santander.gastosapi.services.CategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TestCategoriaController {

  private static final String BUSCA_CAT = "/api/categoria/sugerir";
  private static final Integer ID = Integer.valueOf(1);
  private static final String NOME = "CATEGORIA XYZ";

  @Autowired
  private MockMvc mvc;

  @MockBean
  private CategoriaService categoriaService;

  @Autowired
  ObjectMapper objectMapper;

  @Test
  @WithMockUser
  public void testSugerirCategoria() throws Exception {
    List<Categoria> c = new ArrayList<>();
    c.add(retornaCategoria());
    Page<Categoria> pages = new PageImpl<Categoria>(c);

    BDDMockito.given(this.categoriaService.sugerirCategoria("ttt", 0, 0)).willReturn(pages);

    mvc.perform(MockMvcRequestBuilders.get(BUSCA_CAT)
        .content(objectMapper.writeValueAsString(getCategoriaDTO()))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  private Categoria retornaCategoria() {
    return Categoria.builder()
        .id(ID)
        .nome(NOME)
        .build();
  }

  private SugestaoCategoriaDTO getCategoriaDTO() {
    SugestaoCategoriaDTO dto = new SugestaoCategoriaDTO();
    dto.setDescricao("teste");
    dto.setPagina(0);
    dto.setQtdDeRegistros(5);
    return dto;
  }

}
