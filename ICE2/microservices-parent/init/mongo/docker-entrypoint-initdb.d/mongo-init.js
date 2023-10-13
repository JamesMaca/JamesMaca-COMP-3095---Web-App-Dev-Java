//WILL SEE THIS IN THE MONGODB CONSOLE

//log the start if the script execution
print("START");

//switch to the 'product-service' db (or creat it if it doesnt exists)
db = db.getSiblingDB('product-service');

db.createUser(
    {
        user: 'rootadmin',
        pwd: 'password',
        roles: [{ role: 'readWrite', db: 'product-service'}],
    }
);

//create a new collection named 'user' within the 'product-service' database
db.createCollection('user');

print('END');