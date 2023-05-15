package com.example.onlinebookstore.validator;

import java.util.List;

public interface OrderValidator {

  void validateRequestedBooks(List<Long> bookIds);

}
