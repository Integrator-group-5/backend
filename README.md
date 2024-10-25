# Backend Luxury Wear

## Use project

1. Clone the repository
2. Run docker container (or install MySQL in your machine)
   ```bash
   docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root --name luxurywear_db mysql
   ```
3. Create `.env` file in the root of the project and follow the example in the `.env.example` file
4. Run project in IntelliJ IDEA
5. Open `http://localhost:8080/api/v1/products` in your browser