USE [cs560]
GO

/****** Object:  UserDefinedFunction [dbo].[getage]    Script Date: 5/16/2023 1:39:15 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE function [dbo].[getage] (@pid uniqueidentifier)
returns int
as begin
	declare @age integer
	declare @sql nvarchar(max)
	select @age = ((convert(int, getdate() - dbo.getdob(@pid))) / 365)
	return @age
end
GO


