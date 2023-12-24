alter table book_reservation
    rename column book_id to book_instance_id;
alter table book_reservation
    drop constraint book_reservation_book_id_fkey,
    add constraint book_reservation_book_instance_id_fkey foreign key (book_instance_id)
        references book_instance (id);