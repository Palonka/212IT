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
        DbConn dbConn = new DbConn();
        public BusinessEditor()
        {
            InitializeComponent();
        }
        private void BusinessEditor_Load(object sender, EventArgs e)
        {
            dGVBusinessRecords.DataSource = dbConn.GetAllBusiness();
        }

        private void btnRefresh_Click(object sender, EventArgs e)
        {
            dGVBusinessRecords.DataSource = dbConn.GetAllBusiness();
        }
        private void btnAddNew_Click(object sender, EventArgs e)
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
            btnSaveNew.Enabled = true;
            tbFname.Text = String.Empty;
            tbLname.Text = String.Empty;
            tbTel.Text = String.Empty;
            tbEmail.Text = String.Empty;
            tbAddr1.Text = String.Empty;
            tbAddr2.Text = String.Empty;
            tbCity.Text = String.Empty;
            tbPost.Text = String.Empty;
            tbBTel.Text = String.Empty;
        }

        private void dGVBusinessRecords_CellClick(object sender, DataGridViewCellEventArgs e)
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

        private void btnSaveNew_Click(object sender, EventArgs e)
        {
            BusinessContact businessContact = new BusinessContact();
            businessContact.contactFname = tbFname.Text;
            businessContact.contactLname = tbLname.Text;
            businessContact.contactTel = tbTel.Text;
            businessContact.contactEmail = tbEmail.Text;
            businessContact.contactAddr1 = tbAddr1.Text;
            businessContact.contactAddr2 = tbAddr2.Text;
            businessContact.contactCity = tbCity.Text;
            businessContact.contactPostcode = tbPost.Text;
            businessContact.contactBusinessTel = tbBTel.Text;
            dbConn.InsertBusiness(businessContact);
            tbFname.Enabled = false;
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

        private void btnUpdate_Click(object sender, EventArgs e)
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

        private void btnSave_Click(object sender, EventArgs e)
        {
            int index = Int32.Parse(dGVBusinessRecords.SelectedCells[0].Value.ToString());
            BusinessContact businessContact = new BusinessContact();
            businessContact.ContactID = index;
            businessContact.contactFname = tbFname.Text;
            businessContact.contactLname = tbLname.Text;
            businessContact.contactTel = tbTel.Text;
            businessContact.contactEmail = tbEmail.Text;
            businessContact.contactAddr1 = tbAddr1.Text;
            businessContact.contactAddr2 = tbAddr2.Text;
            businessContact.contactCity = tbCity.Text;
            businessContact.contactPostcode = tbPost.Text;
            businessContact.contactBusinessTel = tbBTel.Text;
            dbConn.UpdateBusiness(businessContact);
            dGVBusinessRecords.DataSource = dbConn.GetAllBusiness();
            tbFname.Enabled = false;
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

        private void btnDelete_Click(object sender, EventArgs e)
        {
            string message = "Are you sure you want to delete?";
            string caption = "Do you want to delete the content with the record with ID of " + Int32.Parse(dGVBusinessRecords.SelectedCells[0].Value.ToString());
            MessageBoxButtons buttons = MessageBoxButtons.YesNo;
            DialogResult result;

            result = MessageBox.Show(message, caption, buttons);
            if (result == DialogResult.Yes)
            {
                dbConn.DeleteBusiness(Int32.Parse(dGVBusinessRecords.SelectedCells[0].Value.ToString()));

                dGVBusinessRecords.DataSource = dbConn.GetAllBusiness();
            }
        }
    }
}
