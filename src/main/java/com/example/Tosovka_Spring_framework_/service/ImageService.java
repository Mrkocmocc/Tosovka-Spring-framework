package com.example.Tosovka_Spring_framework_.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Tosovka_Spring_framework_.entity.Events;
import com.example.Tosovka_Spring_framework_.entity.Images;
import com.example.Tosovka_Spring_framework_.mapper.UserMapper;
import com.example.Tosovka_Spring_framework_.repositories.ImagesRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Autowired
    private final ImagesRepository imagesRepository;
    @Autowired
    private final UserService userService;

    public void saveImage(Events events, byte[] image, Principal principal) {
        Images images = new Images();
        images.setEvents(events);
        images.setImage(image);
        images.setUser(UserMapper.INSTANCE.toEntity(userService.getUserByPrincipal(principal)));
        imagesRepository.save(images);
    }

    @Transactional
    public void deleteImageByEventId(long id) {
        imagesRepository.deleteByEventsId(id);
    }

}
