CREATE OR REPLACE FUNCTION increase_total_number()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE book
    SET total_number = total_number + 1
    WHERE id = NEW.book_id;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_increase_total_number
    AFTER INSERT ON book_instance
    FOR EACH ROW
EXECUTE FUNCTION increase_total_number();