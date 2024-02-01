package fr.donovan.cap_entreprise.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.donovan.cap_entreprise.entity.Gamer;
import fr.donovan.cap_entreprise.entity.User;
import fr.donovan.cap_entreprise.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    public void testAccessReviewAnonymous() throws Exception {
        mockMvc.perform(get("/avis").with(anonymous()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testAccessReviewLogin() throws Exception {
        mockMvc.perform(get("/avis").with(user("bob")))
                .andExpect(status().isOk())
                .andExpect(view().name("review/index"));
    }

    @Test
    public void testAccessReviewLoginModerator() throws Exception {
        mockMvc.perform(get("/avis").with(user("chloe")))
                .andExpect(status().isOk())
                .andExpect(view().name("review/index"));
    }

    @Test
    public void testAccessPageReviewLogin() throws Exception {
        User user = new Gamer();
        user.setNickname("bob");
        mockMvc.perform(get("/avis").with(user("bob")))
                .andExpect(status().isOk())
                .andExpect(model().attribute(
                                "pageReviews",
                                reviewService.findAll(
                                        user,
                                        PageRequest.of(
                                                1,
                                                6,
                                                Sort.by("moderator"
                                                ).ascending())
                                )
                        )
                );
    }

    @Test
    public void testAccessPageReviewLoginModerator() throws Exception {
        mockMvc.perform(get("/avis").with(user("chloe").roles("MODERATOR")))
                .andExpect(content().string(containsString("Total page 84")));
    }

    @Test
    public void testAccessPageReviewLoginModeratorToModerate() throws Exception {
        mockMvc.perform(get("/avis").with(user("chloe").roles("MODERATOR")))
                .andExpect(content().string(containsString("Total page 17")));
    }

    @Test
    public void testAccessPageReviewLoginModeratorModerate() throws Exception {
        mockMvc.perform(get("/avis").with(user("chloe").roles("MODERATOR")))
                .andExpect(content().string(containsString("Total page 67")));
    }
}