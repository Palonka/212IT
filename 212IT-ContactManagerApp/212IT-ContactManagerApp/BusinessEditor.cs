using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _212IT_ContactManagerApp
{
    public partial class BusinessEditor : Form
    {
        DbConn dbConn = new DbConn();//establishes connection with the database
        public BusinessEditor()
        {
            InitializeComponent();//initializes the business editor
        }
        private void BusinessEditor_Load(object sender, EventArgs e)//loads the busienss records
        {
            dGVBusinessRecords.DataSource = dbConn.GetAllBusiness();
        }

        private void btnRefresh_Click(object sender, EventArgs e)
        {
            dGVBusinessRecords.DataSource = dbConn.GetAllBusiness();//refresh functionality is the same as load
        }
        private void btnAddNew_Click(object sender, EventArgs e)//events that happen on button click
        {
            tbFname.Enabled = true;//enables the text boxes
            tbLname.Enabled = true;
            tbTel.Enabled = true;
            tbEmail.Enabled = true;
            tbAddr1.Enabled = true;
            tbAddr2.Enabled = true;
            tbCity.Enabled = true;
            tbPost.Enabled = true;
            tbBTel.Enabled = true;
            btnDelete.Enabled = false;
            btnUpdate.Enabled = false;
            btnSaveNew.Enabled = true;
            tbFname.Text = String.Empty;//makes the fields empty
            tbLname.Text = String.Empty;
            tbTel.Text = String.Empty;
            tbEmail.Text = String.Empty;
            tbAddr1.Text = String.Empty;
            tbAddr2.Text = String.Empty;
            tbCity.Text = String.Empty;
            tbPost.Text = String.Empty;
            tbBTel.Text = String.Empty;
        }

        private void dGVBusinessRecords_CellClick(object sender, DataGridViewCellEventArgs e)//event that will happen after clicking on a specific tuple on the table
        {
            int index = Int32.Parse(dGVBusinessRecords.SelectedCells[0].Value.ToString());
            tbFname.Text = dGVBusinessRecords.SelectedCells[1].Value.ToString();
            tbLname.Text = dGVBusinessRecords.SelectedCells[2].Value.ToString();
            tbTel.Text = dGVBusinessRecords.SelectedCells[3].Value.ToString();
            tbEmail.Text = dGVBusinessRecords.SelectedCells[4].Value.ToString();
            tbAddr1.Text = dGVBusinessRecords.SelectedCells[5].Value.ToString();
            tbAddr2.Text = dGVBusinessRecords.SelectedCells[6].Value.ToString();
            tbCity.Text = dGVBusinessRecords.SelectedCells[7].Value.ToString();
            tbPost.Text = dGVBusinessRecords.SelectedCells[8].Value.ToString();
            tbBTel.Text = dGVBusinessRecords.SelectedCells[9].Value.ToString();
        }

        private void btnSaveNew_Click(object sender, EventArgs e)//save new button fuctionality and settings related to it
        {
            BusinessContact businessContact = new BusinessContact();//creates a new business contact record
            businessContact.contactFname = tbFname.Text;//converts the text to information stored
            businessContact.contactLname = tbLname.Text;
            businessContact.contactTel = tbTel.Text;
            businessContact.contactEmail = tbEmail.Text;
            businessContact.contactAddr1 = tbAddr1.Text;
            businessContact.contactAddr2 = tbAddr2.Text;
            businessContact.contactCity = tbCity.Text;
            businessContact.contactPostcode = tbPost.Text;
            businessContact.contactBusinessTel = tbBTel.Text;
            dbConn.InsertBusiness(businessContact);//calls the inserbusiness comamnd from dbconn.cs
            tbFname.Enabled = false;//disables the text boxes after clicking
            tbLname.Enabled = false;
            tbTel.Enabled = false;
            tbEmail.Enabled = false;
            tbAddr1.Enabled = false;
            tbAddr2.Enabled = false;
            tbCity.Enabled = false;
            tbPost.Enabled = false;
            tbBTel.Enabled = false;
            btnDelete.Enabled = true;
            btnUpdate.Enabled = true;
            btnSaveNew.Enabled = false;
            dGVBusinessRecords.DataSource = dbConn.GetAllBusiness();
        }

        private void btnUpdate_Click(object sender, EventArgs e)//pretty much just enables text boxes and buttons, making the functionality clear
        {
            tbFname.Enabled = true;
            tbLname.Enabled = true;
            tbTel.Enabled = true;
            tbEmail.Enabled = true;
            tbAddr1.Enabled = true;
            tbAddr2.Enabled = true;
            tbCity.Enabled = true;
            tbPost.Enabled = true;
            tbBTel.Enabled = true;
            btnDelete.Enabled = false;
            btnUpdate.Enabled = false;
            btnSave.Enabled = true;
            btnAddNew.Enabled = false;
        }

        private void btnSave_Click(object sender, EventArgs e)//on clicking save
        {
            int index = Int32.Parse(dGVBusinessRecords.SelectedCells[0].Value.ToString());//creates an id
            BusinessContact businessContact = new BusinessContact();
            businessContact.ContactID = index;//the value taken from index
            businessContact.contactFname = tbFname.Text;//text field values
            businessContact.contactLname = tbLname.Text;
            businessContact.contactTel = tbTel.Text;
            businessContact.contactEmail = tbEmail.Text;
            businessContact.contactAddr1 = tbAddr1.Text;
            businessContact.contactAddr2 = tbAddr2.Text;
            businessContact.contactCity = tbCity.Text;
            businessContact.contactPostcode = tbPost.Text;
            businessContact.contactBusinessTel = tbBTel.Text;
            dbConn.UpdateBusiness(businessContact);//command that is run upon collecting the needed information
            dGVBusinessRecords.DataSource = dbConn.GetAllBusiness();//refreshes the table with records
            tbFname.Enabled = false;//going back to the defaulot settings
            tbLname.Enabled = false;
            tbTel.Enabled = false;
            tbEmail.Enabled = false;
            tbAddr1.Enabled = false;
            tbAddr2.Enabled = false;
            tbCity.Enabled = false;
            tbPost.Enabled = false;
            tbBTel.Enabled = false;
            btnDelete.Enabled = true;
            btnUpdate.Enabled = true;
            btnSave.Enabled = false;
        }

        private void btnDelete_Click(object sender, EventArgs e)//delete fuction
        {
            string message = "Are you sure you want to delete?";//safety message
            string caption = "Do you want to delete the content with the record with ID of " + Int32.Parse(dGVBusinessRecords.SelectedCells[0].Value.ToString());//tells the user which record will be  deleted
            MessageBoxButtons buttons = MessageBoxButtons.YesNo;//yes no options
            DialogResult result;

            result = MessageBox.Show(message, caption, buttons);
            if (result == DialogResult.Yes)
            {
                dbConn.DeleteBusiness(Int32.Parse(dGVBusinessRecords.SelectedCells[0].Value.ToString()));//after the user presses yes the delete busienss command will be executed

                dGVBusinessRecords.DataSource = dbConn.GetAllBusiness();//refereshes the page
            }
        }
    }
}
