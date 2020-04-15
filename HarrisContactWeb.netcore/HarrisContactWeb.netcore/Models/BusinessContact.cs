using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace HarrisContactWeb.netcore.Models
{
    public class BusinessContact // model based on the requirements it will later be the base for the table creation
    {
        public int ID { get; set; }
        public string BusinessFname { get; set; }
        public string BusinessLname { get; set; }
        public string BusinessTel { get; set; }
        public string BusinessEmail { get; set; }
        public string BusinessAddr1 { get; set; }
        public string BusinessAddr2 { get; set; }
        public string BusinessCity { get; set; }
        public string BusinessPost { get; set; }
        public string BusinessBTel { get; set; }
    }
}
