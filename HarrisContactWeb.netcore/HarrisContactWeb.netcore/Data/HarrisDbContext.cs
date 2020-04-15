using HarrisContactWeb.netcore.Models;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace HarrisContactWeb.netcore.Data
{
    public class HarrisDbContext : DbContext //context inheriting from
    {
        public HarrisDbContext(DbContextOptions<HarrisDbContext>options) : base(options) 
        {
            
        }
        public DbSet<PersonalContact> PersonalContacts { get; set; }//uses the model form  the models to create the table that will later save and show the records. Same for both business and personal
        public DbSet<BusinessContact> BusinessContacts { get; set; }

    }
}
