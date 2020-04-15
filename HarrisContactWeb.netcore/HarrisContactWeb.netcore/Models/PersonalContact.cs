using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace HarrisContactWeb.netcore.Models
{
    public class PersonalContact// same as business
    {
        public int ID { get; set; }
        public string PersonalFname { get; set; }
        public string PersonalLname { get; set; }
        public string PersonalTel { get; set; }
        public string PersonalEmail { get; set; }
        public string PersonalAddr1 { get; set; }
        public string PersonalAddr2 { get; set; }
        public string PersonalCity { get; set; }
        public string PersonalPost { get; set; }
        public string PersonalPTel { get; set; }
}
}
