package br.com.etechoracio.ingresso.service;

import br.com.etechoracio.ingresso.dto.SalaRequestDTO;
import br.com.etechoracio.ingresso.dto.SalaResponseDTO;
import br.com.etechoracio.ingresso.entity.Sala;
import br.com.etechoracio.ingresso.mapper.SalaMapper;
import br.com.etechoracio.ingresso.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    private SalaRepository salaRepository;
    private SalaMapper salaMapper;

    public SalaService(SalaRepository salaRepository, SalaMapper salaMapper) {
        this.salaRepository = salaRepository;
        this.salaMapper = salaMapper;
    }

    public List<SalaResponseDTO> buscarTodas() {
        return salaMapper.toResponseDTOList(
                salaRepository.findAllByDataExclusaoIsNull()
        );
    }

    public Optional<SalaResponseDTO> buscarPorId(Long id) {
        return salaRepository.findByIdAndDataExclusaoIsNull(id)
                .map(salaMapper::toResponseDTO);
    }

    public SalaResponseDTO criar(SalaRequestDTO salaRequestDTO) {
        Sala sala = salaMapper.toEntity(salaRequestDTO);

        Sala salaSalva = salaRepository.save(sala);

        return salaMapper.toResponseDTO(salaSalva);
    }

}