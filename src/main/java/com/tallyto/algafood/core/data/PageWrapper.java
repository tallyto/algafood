package com.tallyto.algafood.core.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serial;

public class PageWrapper<T> extends PageImpl<T> {
    @Serial
    private static final long serialVersionUID = 1L;

    private Pageable pageable;

    public PageWrapper(Page<T> page, Pageable pageable) {
        super(page.getContent(), pageable, page.getTotalElements());
        this.pageable = pageable;

    }

    @Override
    public Pageable getPageable() {
        return this.pageable;
    }
}
