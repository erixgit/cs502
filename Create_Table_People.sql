USE [cs560]
GO

/****** Object:  Table [dbo].[People]    Script Date: 5/16/2023 12:47:53 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[People](
	[id] [uniqueidentifier] NOT NULL,
	[Name] [nvarchar](70) NULL,
	[State] [char](2) NULL,
	[DOB] [datetime] NULL,
	[Income] [int] NULL,
 CONSTRAINT [PK__People__3213E83F4B90610E] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


