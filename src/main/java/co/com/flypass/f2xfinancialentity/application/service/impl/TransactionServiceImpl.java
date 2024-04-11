package co.com.flypass.f2xfinancialentity.application.service.impl;

import co.com.flypass.f2xfinancialentity.application.service.TransactionService;
import co.com.flypass.f2xfinancialentity.domain.enums.TransactionType;
import co.com.flypass.f2xfinancialentity.infrastructure.exception.MandatoryValueException;
import co.com.flypass.f2xfinancialentity.infrastructure.exception.NotAllowedOperationException;
import co.com.flypass.f2xfinancialentity.application.mapper.TransactionMapper;
import co.com.flypass.f2xfinancialentity.application.model.builder.TransactionBuilder;
import co.com.flypass.f2xfinancialentity.application.model.dto.transaction.ConsignmentDTO;
import co.com.flypass.f2xfinancialentity.application.model.dto.transaction.TransactionDTO;
import co.com.flypass.f2xfinancialentity.application.model.dto.transaction.TransferAccountDTO;
import co.com.flypass.f2xfinancialentity.application.model.dto.transaction.WithdrawalDTO;
import co.com.flypass.f2xfinancialentity.domain.repository.TransactionRepository;
import co.com.flypass.f2xfinancialentity.application.service.ProductService;
import co.com.flypass.f2xfinancialentity.application.service.ProductTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Ing. Danilo Montoya Hernandez;
 * Email: danilo9831montoya@gmail.com
 * @version Id: <b>j2x-financial-entity</b> 10/03/2024, 12:41 PM
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    public static final String REQUIRED_FIELDS_MESSAGE = "Campos requeridos";
    public static final String TRANSACTION_AMOUNT_MUST_MAYOR_ZERO_MESSAGE = "El valor de la transacción debe se mayor de $0 (cero)";
    private final TransactionRepository transactionRepository;
    private final ProductService productService;
    private final ProductTransactionService productTransactionService;
    private final TransactionMapper transactionMapper;

    @Transactional
    @Override
    public TransactionDTO consignment(ConsignmentDTO consignmentDTO) {
        log.debug("Consignment in process ... {}", consignmentDTO);
        if (null == consignmentDTO) {
            log.error("Required consignment DTO");
            throw new MandatoryValueException(REQUIRED_FIELDS_MESSAGE);
        }
        validateTransactionAmount(consignmentDTO.getAmount());
        var destinationProduct = productService.findByAccountNumber(consignmentDTO.getDestinationAccount());
        var productDTO = productTransactionService.consignment(destinationProduct, consignmentDTO.getAmount());
        var transactionModel = new TransactionBuilder()
                .withType(TransactionType.CONSIGNMENT)
                .withDestinationAccount(productDTO.getAccountNumber())
                .withAmount(consignmentDTO.getAmount())
                .withTransactionDate(LocalDateTime.now())
                .build();
        var savedTransaction = transactionRepository.save(transactionModel);
        log.info("Confirm Consignment transaction: {}", savedTransaction);
        return transactionMapper.modelToDTO(savedTransaction);
    }

    public void validateTransactionAmount(double amount) {
        log.debug("Validating transaction amount ...");
        if (amount < 1) {
            log.error("Not allowed transaction with $0 (zero) amount");
            throw new NotAllowedOperationException(TRANSACTION_AMOUNT_MUST_MAYOR_ZERO_MESSAGE);
        }
    }

    @Transactional
    @Override
    public TransactionDTO withdrawal(WithdrawalDTO withdrawalDTO) {
        log.debug("withdrawal in process ... {}", withdrawalDTO);
        if (null == withdrawalDTO) {
            throw new MandatoryValueException(REQUIRED_FIELDS_MESSAGE);
        }

        var sourceProduct = productService.findByAccountNumber(withdrawalDTO.getSourceAccount());
        var productDTO = productTransactionService.withdrawal(sourceProduct, withdrawalDTO.getAmount());
        var transactionModel = new TransactionBuilder()
                .withType(TransactionType.WITHDRAWAL)
                .withSourceAccount(productDTO.getAccountNumber())
                .withAmount(withdrawalDTO.getAmount())
                .withTransactionDate(LocalDateTime.now())
                .build();
        var savedTransaction = transactionRepository.save(transactionModel);
        log.info("Confirm Withdrawal transaction: {}", savedTransaction);
        return transactionMapper.modelToDTO(savedTransaction);
    }

    @Override
    public TransactionDTO transferBetweenAccounts(TransferAccountDTO transferAccountDTO) {
        log.debug("Transfer between accounts in process ... {}", transferAccountDTO);
        if (null == transferAccountDTO) {
            throw new MandatoryValueException(REQUIRED_FIELDS_MESSAGE);
        }
        var sourceProduct = productService.findByAccountNumber(transferAccountDTO.getSourceAccount());
        var destinationProduct = productService.findByAccountNumber(transferAccountDTO.getDestinationAccount());
        productTransactionService.withdrawal(sourceProduct, transferAccountDTO.getAmount());
        productTransactionService.consignment(destinationProduct, transferAccountDTO.getAmount());
        var transactionModel = new TransactionBuilder()
                .withType(TransactionType.TRANSFER_ACCOUNTS)
                .withSourceAccount(transferAccountDTO.getSourceAccount())
                .withDestinationAccount(transferAccountDTO.getDestinationAccount())
                .withAmount(transferAccountDTO.getAmount())
                .withTransactionDate(LocalDateTime.now())
                .build();

        var savedTransaction = transactionRepository.save(transactionModel);
        log.info("Confirm Transfer between accounts transaction: {}", savedTransaction);
        return transactionMapper.modelToDTO(savedTransaction);
    }

    @Override
    public List<TransactionDTO> findAll() {
        return transactionRepository.findAll()
                .stream()
                .map(transactionMapper::modelToDTO)
                .toList();
    }
}
