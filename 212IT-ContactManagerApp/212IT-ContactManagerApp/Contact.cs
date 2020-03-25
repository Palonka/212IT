using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _212IT_ContactManagerApp
{
    public abstract class Contact
    {
        public int ContactID { get; set; }
        public string contactFname { get; set; }
        public string contactLname { get; set; }
        public string contactTel { get; set; }
        public string contactEmail { get; set; }
        public string contactAddr1 { get; set; }
        public string contactAddr2 { get; set; }
        public string contactCity { get; set; }
        public string contactPostcode { get; set; }
    }
}