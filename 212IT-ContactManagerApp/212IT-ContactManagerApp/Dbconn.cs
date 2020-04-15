using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static _212IT_ContactManagerApp.BusinessContact;

namespace _212IT_ContactManagerApp
{
    public class DbConn
    {
        //all database information is here, specifies where to look for it as well as what credentials to use
        private string connString = "Server=db212it.cxoyanmj9kow.us-east-1.rds.amazonaws.com; User ID=admin;Password=Coventry1!;Database=HarrisContactDB";

        public DataTable GetAllPersonal()// this specifies how the getAllPErsonal method will work
        {
            using (var conn = new MySqlConnection(connString))
            {
                conn.Open();//opens connection with the db
                DataTable personalContactDt = new DataTable();//creates a table that shows items
                List<PersonalContact> personalContacts = new List<PersonalContact>();
                using (var cmd = new MySqlCommand("CALL selectAllPersonal();", conn))// uses a saved command (from the db)
                using (var reader = cmd.ExecuteReader())
                    while (reader.Read())
                    {
                        personalContacts.Add(new PersonalContact //gets the information and creates a table
                        {
                            ContactID = reader.GetInt32(0),
                            contactFname = reader.GetString(1),
                            contactLname = reader.GetString(2),
                            contactTel = reader.GetString(3),
                            contactEmail = reader.GetString(4),
                            contactAddr1 = reader.GetString(5),
                            contactAddr2 = reader.GetString(6),
                            contactCity = reader.GetString(7),
                            contactPostcode = reader.GetString(8),
                            contactHomeTel = reader.GetString(9),
                        });
                    }
                personalContactDt.Columns.Add("ContactID");// this will be ho the columns will be named
                personalContactDt.Columns.Add("Contact First Name");
                personalContactDt.Columns.Add("Contact Last Name");
                personalContactDt.Columns.Add("Contact Telephone");
                personalContactDt.Columns.Add("Contact Email");
                personalContactDt.Columns.Add("Contact Address line 1");
                personalContactDt.Columns.Add("Contact Address line 2");
                personalContactDt.Columns.Add("Contact City");
                personalContactDt.Columns.Add("Contact Post Code");
                personalContactDt.Columns.Add("Contact Home Tel");

                foreach (var item in personalContacts)
                {
                    var row = personalContactDt.NewRow();

                    row["ContactID"] = item.ContactID;//assignment of data to specific rows on the table
                    row["Contact First Name"] = item.contactFname;
                    row["Contact Last Name"] = item.contactLname;
                    row["Contact Telephone"] = item.contactTel;
                    row["Contact Email"] = item.contactEmail;
                    row["Contact Address line 1"] = item.contactAddr1;
                    row["Contact Address line 2"] = item.contactAddr2;
                    row["Contact City"] = item.contactCity;
                    row["Contact Post Code"] = item.contactPostcode;
                    row["Contact Home Tel"] = item.contactHomeTel;

                    personalContactDt.Rows.Add(row);
                }
                return personalContactDt;
            }
        }
        public DataTable GetAllBusiness()// different command same as before
        {
            using (var conn = new MySqlConnection(connString))
            {
                conn.Open();
                DataTable businessContactDt = new DataTable();
                List<BusinessContact> businessContacts = new List<BusinessContact>();
                using (var cmd = new MySqlCommand("CALL selectAllBusiness();", conn))
                using (var reader = cmd.ExecuteReader())
                    while (reader.Read())
                    {
                        businessContacts.Add(new BusinessContact
                        {
                            ContactID = reader.GetInt32(0),
                            contactFname = reader.GetString(1),
                            contactLname = reader.GetString(2),
                            contactTel = reader.GetString(3),
                            contactEmail = reader.GetString(4),
                            contactAddr1 = reader.GetString(5),
                            contactAddr2 = reader.GetString(6),
                            contactCity = reader.GetString(7),
                            contactPostcode = reader.GetString(8),
                            contactBusinessTel = reader.GetString(9),
                        });
                    }
                businessContactDt.Columns.Add("ContactID");
                businessContactDt.Columns.Add("Contact First Name");
                businessContactDt.Columns.Add("Contact Last Name");
                businessContactDt.Columns.Add("Contact Telephone");
                businessContactDt.Columns.Add("Contact Email");
                businessContactDt.Columns.Add("Contact Address line 1");
                businessContactDt.Columns.Add("Contact Address line 2");
                businessContactDt.Columns.Add("Contact City");
                businessContactDt.Columns.Add("Contact Post Code");
                businessContactDt.Columns.Add("Contact Business Tel");

                foreach (var item in businessContacts)
                {
                    var row = businessContactDt.NewRow();

                    row["ContactID"] = item.ContactID;
                    row["Contact First Name"] = item.contactFname;
                    row["Contact Last Name"] = item.contactLname;
                    row["Contact Telephone"] = item.contactTel;
                    row["Contact Email"] = item.contactEmail;
                    row["Contact Address line 1"] = item.contactAddr1;
                    row["Contact Address line 2"] = item.contactAddr2;
                    row["Contact City"] = item.contactCity;
                    row["Contact Post Code"] = item.contactPostcode;
                    row["Contact Business Tel"] = item.contactBusinessTel;

                    businessContactDt.Rows.Add(row);
                }
                return businessContactDt;
            }
        }
        public async void InsertPersonal(PersonalContact personalContact)//another one
        {
            using (var conn = new MySqlConnection(connString))
            {
                await conn.OpenAsync();//asyncronous code for better performance
                using (var cmd = new MySqlCommand())
                {
                    cmd.Connection = conn;//opens connection
                    cmd.CommandText = "CALL INSERTPersonal(@p1,@p2,@p3,@p4,@p5,@p6,@p7,@p8,@p9);";//uses the stored procedure
                    cmd.Parameters.AddWithValue("p1", personalContact.contactFname);//explains assignment od information
                    cmd.Parameters.AddWithValue("p2", personalContact.contactLname);
                    cmd.Parameters.AddWithValue("p3", personalContact.contactTel);
                    cmd.Parameters.AddWithValue("p4", personalContact.contactEmail);
                    cmd.Parameters.AddWithValue("p5", personalContact.contactAddr1);
                    cmd.Parameters.AddWithValue("p6", personalContact.contactAddr2);
                    cmd.Parameters.AddWithValue("p7", personalContact.contactCity);
                    cmd.Parameters.AddWithValue("p8", personalContact.contactPostcode);
                    cmd.Parameters.AddWithValue("p9", personalContact.contactHomeTel);
                    await cmd.ExecuteNonQueryAsync();//makes the query run
                }

            }
        }
        public async void InsertBusiness(BusinessContact businessContact)//insert business command
        {
            using (var conn = new MySqlConnection(connString))
            {
                await conn.OpenAsync();
                using (var cmd = new MySqlCommand())
                {
                    cmd.Connection = conn;
                    cmd.CommandText = "CALL INSERTBusiness(@p1,@p2,@p3,@p4,@p5,@p6,@p7,@p8,@p9);";
                    cmd.Parameters.AddWithValue("p1", businessContact.contactFname);
                    cmd.Parameters.AddWithValue("p2", businessContact.contactLname);
                    cmd.Parameters.AddWithValue("p3", businessContact.contactTel);
                    cmd.Parameters.AddWithValue("p4", businessContact.contactEmail);
                    cmd.Parameters.AddWithValue("p5", businessContact.contactAddr1);
                    cmd.Parameters.AddWithValue("p6", businessContact.contactAddr2);
                    cmd.Parameters.AddWithValue("p7", businessContact.contactCity);
                    cmd.Parameters.AddWithValue("p8", businessContact.contactPostcode);
                    cmd.Parameters.AddWithValue("p9", businessContact.contactBusinessTel);
                    await cmd.ExecuteNonQueryAsync();
                }

            }
        }
        public async void UpdatePersonal(PersonalContact personalContact)//update a record
        {
            using (var conn = new MySqlConnection(connString))
            {
                await conn.OpenAsync();
                using (var cmd = new MySqlCommand())
                {
                    cmd.Connection = conn;
                    cmd.CommandText = "CALL updatePersonal(@p0,@p1,@p2,@p3,@p4,@p5,@p6,@p7,@p8,@p9);";
                    cmd.Parameters.AddWithValue("p0", personalContact.ContactID);
                    cmd.Parameters.AddWithValue("p1", personalContact.contactFname);
                    cmd.Parameters.AddWithValue("p2", personalContact.contactLname);
                    cmd.Parameters.AddWithValue("p3", personalContact.contactTel);
                    cmd.Parameters.AddWithValue("p4", personalContact.contactEmail);
                    cmd.Parameters.AddWithValue("p5", personalContact.contactAddr1);
                    cmd.Parameters.AddWithValue("p6", personalContact.contactAddr2);
                    cmd.Parameters.AddWithValue("p7", personalContact.contactCity);
                    cmd.Parameters.AddWithValue("p8", personalContact.contactPostcode);
                    cmd.Parameters.AddWithValue("p9", personalContact.contactHomeTel);
                    await cmd.ExecuteNonQueryAsync();
                }

            }
        }
        public async void UpdateBusiness(BusinessContact businessContact)//update a record
        {
            using (var conn = new MySqlConnection(connString))
            {
                await conn.OpenAsync();
                using (var cmd = new MySqlCommand())
                {
                    cmd.Connection = conn;
                    cmd.CommandText = "CALL updateBusiness(@p0,@p1,@p2,@p3,@p4,@p5,@p6,@p7,@p8,@p9);";
                    cmd.Parameters.AddWithValue("p0", businessContact.ContactID);
                    cmd.Parameters.AddWithValue("p1", businessContact.contactFname);
                    cmd.Parameters.AddWithValue("p2", businessContact.contactLname);
                    cmd.Parameters.AddWithValue("p3", businessContact.contactTel);
                    cmd.Parameters.AddWithValue("p4", businessContact.contactEmail);
                    cmd.Parameters.AddWithValue("p5", businessContact.contactAddr1);
                    cmd.Parameters.AddWithValue("p6", businessContact.contactAddr2);
                    cmd.Parameters.AddWithValue("p7", businessContact.contactCity);
                    cmd.Parameters.AddWithValue("p8", businessContact.contactPostcode);
                    cmd.Parameters.AddWithValue("p9", businessContact.contactBusinessTel);
                    await cmd.ExecuteNonQueryAsync();
                }

            }
        }
        public async void DeletePersonal(int id)//delete a personal record
        {
            using (var conn = new MySqlConnection(connString))
            {
                await conn.OpenAsync();
                using (var cmd = new MySqlCommand())
                {
                    cmd.Connection = conn;
                    cmd.CommandText = "CALL deletePersonal(@p0);";
                    cmd.Parameters.AddWithValue("p0", id);//only based on ID
                    await cmd.ExecuteNonQueryAsync();
                }

            }
        }
        public async void DeleteBusiness(int id)//delete a business record
        {
            using (var conn = new MySqlConnection(connString))
            {
                await conn.OpenAsync();
                using (var cmd = new MySqlCommand())
                {
                    cmd.Connection = conn;
                    cmd.CommandText = "CALL deleteBusiness(@p0);";
                    cmd.Parameters.AddWithValue("p0", id);//only based on ID
                    await cmd.ExecuteNonQueryAsync();
                }

            }
        }
    }
}