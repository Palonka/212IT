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
    public partial class PersonalEditor : Form
    {
        DbConn dbConn = new DbConn();
        public PersonalEditor()
        {
            InitializeComponent();
        }
        private void PersonalEditor_Load(object sender, EventArgs e)
        {
            dGVPersonalRecords.DataSource = dbConn.GetAllPersonal();
        }

        private void btnRefresh_Click(object sender, EventArgs e)
        {
            dGVPersonalRecords.DataSource = dbConn.GetAllPersonal();
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
            tbPTel.Enabled = true;
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
            tbPTel.Text = String.Empty;
        }

        private void dGVPersonalRecords_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            int index = Int32.Parse(dGVPersonalRecords.SelectedCells[0].Value.ToString());
            tbFname.Text = dGVPersonalRecords.SelectedCells[1].Value.ToString();
            tbLname.Text = dGVPersonalRecords.SelectedCells[2].Value.ToString();
            tbTel.Text = dGVPersonalRecords.SelectedCells[3].Value.ToString();
            tbEmail.Text = dGVPersonalRecords.SelectedCells[4].Value.ToString();
            tbAddr1.Text = dGVPersonalRecords.SelectedCells[5].Value.ToString();
            tbAddr2.Text = dGVPersonalRecords.SelectedCells[6].Value.ToString();
            tbCity.Text = dGVPersonalRecords.SelectedCells[7].Value.ToString();
            tbPost.Text = dGVPersonalRecords.SelectedCells[8].Value.ToString();
            tbPTel.Text = dGVPersonalRecords.SelectedCells[9].Value.ToString();
        }

        private void btnSaveNew_Click(object sender, EventArgs e)
        {
            PersonalContact personalContact = new PersonalContact();
            personalContact.contactFname = tbFname.Text;
            personalContact.contactLname = tbLname.Text;
            personalContact.contactTel = tbTel.Text;
            personalContact.contactEmail = tbEmail.Text;
            personalContact.contactAddr1 = tbAddr1.Text;
            personalContact.contactAddr2 = tbAddr2.Text;
            personalContact.contactCity = tbCity.Text;
            personalContact.contactPostcode = tbPost.Text;
            personalContact.contactHomeTel = tbPTel.Text;
            dbConn.InsertPersonal(personalContact);
            tbFname.Enabled = false;
            tbLname.Enabled = false;
            tbTel.Enabled = false;
            tbEmail.Enabled = false;
            tbAddr1.Enabled = false;
            tbAddr2.Enabled = false;
            tbCity.Enabled = false;
            tbPost.Enabled = false;
            tbPTel.Enabled = false;
            btnDelete.Enabled = true;
            btnUpdate.Enabled = true;
            btnSaveNew.Enabled = false;
            dGVPersonalRecords.DataSource = dbConn.GetAllPersonal();
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
            tbPTel.Enabled = true;
            btnDelete.Enabled = false;
            btnUpdate.Enabled = false;
            btnSave.Enabled = true;
            btnAddNew.Enabled = false;
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            int index = Int32.Parse(dGVPersonalRecords.SelectedCells[0].Value.ToString());
            PersonalContact personalContact = new PersonalContact();
            personalContact.ContactID = index;
            personalContact.contactFname = tbFname.Text;
            personalContact.contactLname = tbLname.Text;
            personalContact.contactTel = tbTel.Text;
            personalContact.contactEmail = tbEmail.Text;
            personalContact.contactAddr1 = tbAddr1.Text;
            personalContact.contactAddr2 = tbAddr2.Text;
            personalContact.contactCity = tbCity.Text;
            personalContact.contactPostcode = tbPost.Text;
            personalContact.contactHomeTel = tbPTel.Text;
            dbConn.UpdatePersonal(personalContact);
            dGVPersonalRecords.DataSource = dbConn.GetAllPersonal();
            tbFname.Enabled = false;
            tbLname.Enabled = false;
            tbTel.Enabled = false;
            tbEmail.Enabled = false;
            tbAddr1.Enabled = false;
            tbAddr2.Enabled = false;
            tbCity.Enabled = false;
            tbPost.Enabled = false;
            tbPTel.Enabled = false;
            btnDelete.Enabled = true;
            btnUpdate.Enabled = true;
            btnSave.Enabled = false;
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            string message = "Are you sure you want to delete?";
            string caption= "Do you want to delete the content with the record with ID of "+ Int32.Parse(dGVPersonalRecords.SelectedCells[0].Value.ToString());
            MessageBoxButtons buttons = MessageBoxButtons.YesNo;
            DialogResult result;

            result = MessageBox.Show(message, caption, buttons);
            if (result == DialogResult.Yes)
            {
                dbConn.DeletePersonal(Int32.Parse(dGVPersonalRecords.SelectedCells[0].Value.ToString()));

                dGVPersonalRecords.DataSource = dbConn.GetAllPersonal();
            }
        }
    }
}
