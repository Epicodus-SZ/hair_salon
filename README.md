These are the instructions on how to restore our database:

1. (prompt) postgres
2. (prompt) createdb -T template0 to_do
3. (prompt) to_do < to_do_backfile
4. (prompt) createdb -T to_do to_do_test
