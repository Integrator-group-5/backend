package com.luxury.wear.service.service.size;

import com.luxury.wear.service.commons.Constants;
import com.luxury.wear.service.entity.Size;
import com.luxury.wear.service.exception.ResourceNotFoundException;
import com.luxury.wear.service.repository.SizeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    @Override
    public List<Size> getAllSizes() {
        return sizeRepository.findAll();
    }

    @Override
    public Size getSizeById(Long id) {
        return sizeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Constants.ERROR_SIZE_NOT_FOUND_ID + id)
        );
    }
}
