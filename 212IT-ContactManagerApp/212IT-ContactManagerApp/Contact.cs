﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _212IT_ContactManagerApp
{
    public abstract class Contact// the abstract class responisble for being the blueprint for both Business and personal Contact classes
    {
        public int ContactID { get; set; }//the primary key automatily generated by the using this particular framework
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