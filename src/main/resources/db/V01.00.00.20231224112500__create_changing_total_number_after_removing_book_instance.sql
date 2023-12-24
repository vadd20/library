CREATE OR REPLACE FUNCTION reduce_total_number()
    RETURNS TRIGGER AS $$
BEGIN
    UPDATE book
    SET total_number = total_number - 1
    WHERE id = OLD.book_id;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_reduce_total_number
    AFTER DELETE ON book_instance
    FOR EACH ROW
EXECUTE FUNCTION reduce_total_number();